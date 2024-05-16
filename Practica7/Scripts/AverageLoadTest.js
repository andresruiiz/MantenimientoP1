import http from "k6/http";
import { check, sleep } from "k6";

export const options = {
    stages: [
    { duration: '5m', target: 3000 }, // subimos a una carga promedio del 50% del punto de rotura en 5 minutos
    { duration: '20m', target: 3000 }, // nos mantenemos al 50% del punto de rotura durante 20 minutos
    { duration: '5m', target: 0 }, // bajamos a 0 VUs
    ],
    thresholds: {
        http_req_failed: [
            {
                threshold: "rate<=0.01",
                abortOnFail: true,
            },
        ],
    },
};

export default () => {
  const response = http.get("http://localhost:8080/medico/1");

  check(response, {
    "Response code was 200": (res) => res.status == 200,
  });

  sleep(1);
};