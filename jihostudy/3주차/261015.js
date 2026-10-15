function print_2d_array(array) {
  for (const arr of array) {
    console.log(arr);
  }
}
function solution(maps) {
  const [N, M] = [maps.length, maps[0].length];

  const drow = [0, 1, 0, -1];
  const dcol = [1, 0, -1, 0];

  const queue = [];
  const visited = Array.from(new Array(N), () => new Array(M).fill(false));
  // print_2d_array(visited);

  queue.push([0, 0, 1]);
  visited[0][0] = true;

  let answer = -1;
  while (queue.length != 0) {
    const [row, col, count] = queue.shift();
    // 종료조건
    if (row == N - 1 && col == M - 1) {
      if (answer == -1) {
        answer = count;
      } else {
        answer = Math.min(answer, count);
      }
      continue;
    }
    // 건너뛰기 조건
    // if (answer != -1 && count > answer) {
    //   break;
    // }

    // 순회
    for (let i = 0; i < 4; i++) {
      const nRow = row + drow[i];
      const nCol = col + dcol[i];

      if (
        nRow >= 0 &&
        nRow < N &&
        nCol >= 0 &&
        nCol < M &&
        visited[nRow][nCol] == false &&
        maps[nRow][nCol] != 0
      ) {
        queue.push([nRow, nCol, count + 1]);
        visited[nRow][nCol] = true;
      }
    }
  }
  return answer;
}

console.log(
  solution([
    [1, 0, 1, 1, 1],
    [1, 0, 1, 0, 1],
    [1, 0, 1, 1, 1],
    [1, 1, 1, 0, 1],
    [0, 0, 0, 0, 1],
  ]),
);

console.log(
  solution([
    [1, 0, 1, 1, 1],
    [1, 0, 1, 0, 1],
    [1, 0, 1, 1, 1],
    [1, 1, 1, 0, 0],
    [0, 0, 0, 0, 1],
  ]),
);
