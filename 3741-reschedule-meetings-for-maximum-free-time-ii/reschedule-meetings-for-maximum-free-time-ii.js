var maxFreeTime = function (eventTime, startTime, endTime) {
    const n = startTime.length;
    const q = new Array(n).fill(false);
    let t1 = 0,
        t2 = 0;
    for (let i = 0; i < n; i++) {
        if (endTime[i] - startTime[i] <= t1) {
            q[i] = true;
        }
        t1 = Math.max(t1, startTime[i] - (i === 0 ? 0 : endTime[i - 1]));

        if (endTime[n - i - 1] - startTime[n - i - 1] <= t2) {
            q[n - i - 1] = true;
        }
        t2 = Math.max(
            t2,
            (i === 0 ? eventTime : startTime[n - i]) - endTime[n - i - 1],
        );
    }

    let res = 0;
    for (let i = 0; i < n; i++) {
        const left = i === 0 ? 0 : endTime[i - 1];
        const right = i === n - 1 ? eventTime : startTime[i + 1];
        if (q[i]) {
            res = Math.max(res, right - left);
        } else {
            res = Math.max(res, right - left - (endTime[i] - startTime[i]));
        }
    }
    return res;
};