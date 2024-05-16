import http from "k6/http";
import { check, sleep } from "k6";

export const options = {
    stages: [
    { duration: '5m', target: 1500 }, // subimos a un carga promedio de 1500 vu en 5 minutos
    { duration: '20m', target: 1500 }, // no mantenemos a 2000 por 30 minutos
    { duration: '5m', target: 0 }, // bajamos a 0VU
    ],
};

export default () => {
  const response = http.get("http://localhost:8080/medico/1");

  check(response, {
    "Response code was 200": (res) => res.status == 200,
  });

  sleep(1);
};
