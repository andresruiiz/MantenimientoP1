import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
    stages: [
    { duration: '10m', target: 2000 }, // subimos a un carga de estres de 2000 vu en 10 minutos
    { duration: '30m', target: 2000 }, // nos mantenemos a 2000 por 30 minutos
    { duration: '5m', target: 0 }, // bajamos a 0 
    VU
    ],
    };



export default () => {
const response = http.get('http://localhost:8080/medico/1');
check(response, {
    'Response code was 200': (res) => res.status == 200
    });
sleep(1);
};
