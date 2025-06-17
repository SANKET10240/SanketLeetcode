const MOD = BigInt(1e9 + 7);
const MAX = 100000;
const fac = Array(MAX).fill(0n);
const inv = Array(MAX).fill(0n);

const pow = (x, y) => {
    x = BigInt(x);
    y = BigInt(y);
    let res = 1n;
    while (y > 0n) {
        if (y & 1n) res = (res * x) % MOD;
        x = (x * x) % MOD;
        y >>= 1n;
    }
    return res;
};

const prep = () => {
    if (fac[0] !== 0n) return;
    fac[0] = 1n;
    for (let i = 1; i < MAX; i++) fac[i] = (fac[i - 1] * BigInt(i)) % MOD;
    inv[MAX - 1] = pow(fac[MAX - 1], MOD - 2n);
    for (let i = MAX - 2; i >= 0; i--) inv[i] = (inv[i + 1] * BigInt(i + 1)) % MOD;
};

const binom = (n, r) => {
    if (r < 0 || r > n) return 0n;
    return (((fac[n] * inv[r]) % MOD) * inv[n - r]) % MOD;
};

const countGoodArrays = (n, m, k) => {
    prep();
    let ways = binom(n - 1, k);
    ways = (ways * BigInt(m)) % MOD;
    ways = (ways * pow(m - 1, n - k - 1)) % MOD;
    return Number(ways);
};