import { browser } from 'k6/experimental/browser';
import { check, sleep } from 'k6';


export const options = {
  scenarios: {
    ui: {
      executor: 'shared-iterations',
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

    const pacienteRow = page.$$("table tbody tr")[0];
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), pacienteRow.click()]);

    page.waitForSelector('table tbody');
    sleep(2);

    const viewButton = page.$$("table tbody tr")[len-1].$('input[name="view"]');
    await Promise.all([page.waitForNavigation(), viewButton.click()]);

    const predictButton = page.locator('button[name="predict"]');
    await Promise.all([page.waitForNavigation(), predictButton.click()]);

    page.waitForSelector('span');

    check(page, {
      'prediction': p => p.locator('span[name="predict"]').textContent() == 'Probabilidad de cáncer:',
    });

    sleep(3);
  } finally {
    page.close();
  }
}