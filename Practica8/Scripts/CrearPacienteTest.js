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

    const addPacientButton = page.locator('button[name="add"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), addPacientButton.click()]);
  
    page.locator('input[name="dni"]').type('3');
    page.locator('input[name="nombre"]').type('PacienteTest');
    page.locator('input[name="edad"]').type('33');
    page.locator('input[name="cita"]').type('APorLa33');
    sleep(3); // Esperamos a que se cargue el modal

    const crearButton = page.locator('button[type="submit"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), crearButton.click()]);
    sleep(3);


    let len = page.$$("table tbody tr").length; // Comprobamos que se ha añadido el paciente
    console.log(len);
    console.log(page.$$("table tbody tr")[len-1].$('td[name="dni"]').textContent());
    check(page, {
      'DNI created': p => parseInt(p.$$("table tbody tr")[len-1].$('td[name="dni"]').textContent()) == 3,
      'Nombre created': p => p.$$("table tbody tr")[len-1].$('td[name="nombre"]').textContent().includes('PacienteTest'), 
    });

  } finally {
    page.close();
  }
}