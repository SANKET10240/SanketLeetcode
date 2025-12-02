var grayCode = function(n) {
    const result = [];
    const total = 1 << n;
    for (let i = 0; i < total; i++) {
        result.push(i ^ (i >> 1));
    }
    return result;
};