import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
    stages: [
        { duration: '3m', target: 4800 }, // subimos a un carga de estres de 1600 vu (80% del punto de rotura) en 3 minutos
        { duration: '3m', target: 4800 }, // nos mantenemos a 1600 por 3 minutos
        { duration: '2m', target: 0 }, // bajamos a 0 VUs en 2 minutos
    ],
    thresholds: {
        http_req_failed: [{
        threshold: 'rate<=0.01', // Las peticiones fallidas deben ser inferiores al 1%
        abortOnFail: true // La duración media de las peticiones debe ser inferior a 1000 ms
        }]},
        http_req_duration: ['avg<1000'],
};

export default () => {
    const response = http.get('http://localhost:8080/medico/1');
    check(response, {
        'Response code was 200': (res) => res.status == 200
    });
    sleep(1);
};