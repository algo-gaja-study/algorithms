class MinHeap {
    constructor() {
        this.heap = [];
    }

    size() {
        return this.heap.length;
    }

    peek() {
        return this.heap[0];
    }

    push(value) {
        const h = this.heap;
        h.push(value);

        // 올라가면서 부모와 비교 (상향식 정렬)
        let cur = h.length - 1;
        while (cur > 0) {
            const parent = (cur - 1) >> 1;
            if (h[parent] <= h[cur]) break;
            [h[parent], h[cur]] = [h[cur], h[parent]];
            cur = parent;
        }
    }

    pop() {
        const h = this.heap;
        const min = h[0];
        const last = h.pop();

        if (h.length > 0) {
            h[0] = last;

            // 내려가면서 더 작은 자식과 교환 (하향식 정렬)
            let cur = 0;
            while (true) {
                const left = cur * 2 + 1;
                const right = cur * 2 + 2;
                let smallest = cur;

                if (left < h.length && h[left] < h[smallest]) smallest = left;
                if (right < h.length && h[right] < h[smallest]) smallest = right;
                if (smallest === cur) break;

                [h[smallest], h[cur]] = [h[cur], h[smallest]];
                cur = smallest;
            }
        }

        return min;
    }
}

function solution(scoville, K) {
    const heap = new MinHeap();
    for (const s of scoville) heap.push(s);

    let count = 0;

    // 가장 안 매운 음식이 K 미만인 동안 계속 섞는다
    while (heap.size() >= 2 && heap.peek() < K) {
        const first = heap.pop();   // 가장 안 매운 음식
        const second = heap.pop();  // 두 번째로 안 매운 음식

        heap.push(first + second * 2);
        count++;
    }

    // 하나만 남았는데도 K 미만이면 불가능
    return heap.peek() >= K ? count : -1;
}
