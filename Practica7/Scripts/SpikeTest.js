import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
    stages: [
        { duration: '2m', target: 2400 }, // subimos a un carga de estres de 4000 vu (40% del punto de rotura) en 2 minutos
        { duration: '2m', target: 0 }, // bajamos a 0 VUs en 2 minutos
    ],
    thresholds: {
        http_req_failed: ['rate<0.005'],  // Las peticiones fallidas deben ser inferiores al 0.5%
    },
};

export default () => {
    const response = http.get('http://localhost:8080/medico/1');
    check(response, {
        'Response code was 200': (res) => res.status == 200
    });
    sleep(1);
};