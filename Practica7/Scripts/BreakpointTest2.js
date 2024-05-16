import http from "k6/http";
import { check, sleep } from "k6";

export const options = {
    stages: [
    { duration: '10m', target: 100000 }, // just slowly ramp-up to a HUGE load
    ],
    thresholds: {
    http_req_failed: [{
    threshold: 'rate<=0.01',
    abortOnFail: true,
    }]}
};

export default () => {
  const response = http.get("http://localhost:8080/medico/1");

  check(response, {
    "Response code was 200": (res) => res.status == 200,
  });

  sleep(1);
};
