import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
vus: 5, // Clave para la prueba de humo. Manténgalo en 2, 3, máximo 5 Vus
duration: '1m', // Esto puede ser más corto o sólo unas pocas iteraciones
thresholds: {
    http_req_failed: [{
    threshold: 'rate<=0.05',
    abortOnFail: true,
    }],
    http_req_duration: ['avg<=100'],
    }
};



export default () => {
const response = http.get('http://localhost:8080/medico/1');
check(response, {
    'Response code was 200': (res) => res.status == 200
    });
sleep(1);
};
