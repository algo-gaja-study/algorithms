function solution(fees, records) {
    var answer = [];
    const defaultTime = fees[0]
    const defaultFee = fees[1];
    const unitTime = fees[2];
    const unitFee = fees[3];

    const inRecord = new Map();
    const totalTime = new Map();

    const toMinutes = (time) => {
        const [hour, minute] = time.split(":").map(Number);
        return hour * 60 + minute;
    }

    const calculate = (time) => {
        if (time <= defaultTime) return defaultFee;

        const extraTime = time - defaultTime;
        return defaultFee + Math.ceil(extraTime / unitTime) * unitFee;
    };

    for (const record of records) {
        const [time, car, status] = record.split(" ");

        if (status === 'IN') {
            inRecord.set(car, toMinutes(time));
        }
        else {
            const diff = toMinutes(time) - inRecord.get(car);

            totalTime.set(car, (totalTime.get(car) ?? 0) + diff);
            inRecord.delete(car);
        }
    }

     // 출차하지 않은 차량
    for (const [car, time] of inRecord) {
        const diff = toMinutes("23:59") - time;
        totalTime.set(car, (totalTime.get(car) ?? 0) + diff);
    }

    answer = [...totalTime.entries()].sort((a, b) => a[0].localeCompare(b[0])).map(([car, time]) => calculate(time));
    return answer;
}
