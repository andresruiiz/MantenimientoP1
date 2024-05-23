import { browser } from 'k6/experimental/browser';
import { check, sleep } from 'k6';


export const options = {
  scenarios: {
    ui: {
      executor: 'shared-iterations', // para realizar iteraciones sin indicar el tiempo
      options: {
        browser: {
          type: 'chromium',
        }
      }
    }
  },
  thresholds: {
    checks: ["rate==1.0"]
  }
}

export default async function () {
  const page = browser.newPage();
  try {
    await page.goto('http://localhost:4200/');

    page.locator('input[name="nombre"]').type('Luis');
    page.locator('input[name="DNI"]').type('123123');
    
    const submitButton = page.locator('button[name="login"]');

    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), submitButton.click()]);
    sleep(2);

    const pacienteRow = page.$$("table tbody tr")[0];
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), pacienteRow.click()]);

    page.waitForSelector('table tbody');
    sleep(2);

    const viewButton = page.$$("table tbody tr")[0].$('button[name="view"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), viewButton.click()]);

    const informeButton = page.locator('button[title="Añadir informe"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), informeButton.click()]);

    sleep(5);
    page.locator('textarea.mat-mdc-input-element.mdc-text-field__input').type('Informe de prueba');
    sleep(10);

    const saveButton = page.locator('button[name="save"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), saveButton.click()]);

    check(page, {
  'informe': p => p.locator('span.info-value').textContent().includes('Informe de prueba'),
});

  } finally {
    page.close();
  }
}
