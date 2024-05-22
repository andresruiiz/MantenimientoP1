import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
    stages: [
        { duration: '2m', target: 3700 },
        { duration: '2m', target: 0 },
    ],
    thresholds: {
        http_req_failed: ['rate<0.005'],
    },
};

export default () => {
    const response = http.get('http://localhost:8080/medico/1');
    check(response, {
        'Response code was 200': (res) => res.status == 200
    });
    sleep(1);
};