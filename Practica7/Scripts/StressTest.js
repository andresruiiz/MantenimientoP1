import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
    stages: [
        { duration: '3m', target: 4800 },
        { duration: '3m', target: 4800 },
        { duration: '2m', target: 0 },
    ],
    thresholds: {
        http_req_failed: [{
        threshold: 'rate<=0.01',
        abortOnFail: true
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