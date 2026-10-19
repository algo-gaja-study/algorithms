// 두 시간 사이의 입차 간격을 구하는 것 (time1 <= time2)
function getInTime(time1, time2) {
  // NOTE: .map(Number) 를 통해 parseInt 가능
  const [hour1, minute1] = time1.split(":").map(Number);
  const [hour2, minute2] = time2.split(":").map(Number);

  return 60 * hour2 + minute2 - (60 * hour1 + minute1);
}

function solution(fees, records) {
  const IN_CARS = {}; // 주차장에 들어있는 차들의 입장시간
  const charges = {}; // 각 차량별 이용 시간
  for (const record of records) {
    const [time, car_number, spec] = record.split(" ");
    console.log(time, car_number, spec);

    // 입차
    if (spec == "IN") {
      IN_CARS[car_number] = time;
    }
    // 출차
    else if (spec == "OUT") {
      const in_time = IN_CARS[car_number]; // ex. 05:34
      charges[car_number] =
        (charges[car_number] || 0) + getInTime(in_time, time);
      delete IN_CARS[car_number];
    }
  }

  // NOTE: Object.entires(obj) 를 반환할때 각각은 배열로 반환된다.
  // 예외 : '어떤 차량이 입차된 후에 출차된 내역이 없다면, 23:59에 출차된 것으로 간주'
  for (const [car_number, in_time] of Object.entries(IN_CARS)) {
    charges[car_number] =
      (charges[car_number] || 0) + getInTime(in_time, "23:59");
  }
  console.log(charges);

  // 2. 차량 번호 작은 숫자부터 주차 요금 반환
  const new_charges = [];
  for (const [car_number, charge] of Object.entries(charges)) {
    new_charges.push([Number(car_number), charge]);
  }

  new_charges.sort((a, b) => a[0] - b[0]);
  console.log("new_charges: ", new_charges);

  const times = new_charges.map((value) => value[1]);
  console.log(times);

  // 3. 시간을 금액으로 변환
  const [basic_time, basic_charge, tick_time, tick_charge] = fees;

  const answers = [];
  for (const time of times) {
    console.log(time);

    // '누적 주차 시간이 기본 시간이하라면, 기본 요금을 청구합니다.'
    if (time <= basic_time) {
      answers.push(basic_charge);
      console.log("1: ", basic_charge);
    }
    // '누적 주차 시간이 기본 시간을 초과하면, 기본 요금에 더해서, 초과한 시간에 대해서 단위 시간 마다 단위 요금을 청구합니다.'
    else if (time > basic_time) {
      answers.push(
        basic_charge + Math.ceil((time - basic_time) / tick_time) * tick_charge,
      );
    }
  }
  return answers;
}

// console.log(
//   solution(
//     [180, 5000, 10, 600],
//     [
//       "05:34 5961 IN",
//       "06:00 0000 IN",
//       "06:34 0000 OUT",
//       "07:59 5961 OUT",
//       "07:59 0148 IN",
//       "18:59 0000 IN",
//       "19:09 0148 OUT",
//       "22:59 5961 IN",
//       "23:00 5961 OUT",
//     ],
//   ),
// );

// console.log(
//   solution(
//     [120, 0, 60, 591],
//     [
//       "16:00 3961 IN",
//       "16:00 0202 IN",
//       "18:00 3961 OUT",
//       "18:00 0202 OUT",
//       "23:58 3961 IN",
//     ],
//   ),
// );

console.log(solution([1, 461, 1, 10], ["00:00 1234 IN"]));
