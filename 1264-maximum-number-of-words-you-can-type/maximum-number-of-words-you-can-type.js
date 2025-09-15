const canBeTypedWords = (text, broken) => {
    let mask = 0;
    for (let i = 0; i < broken.length; i++) {
        mask |= 1 << (broken.charCodeAt(i) - 97);
    }

    let count = 0, brokenWord = false;
    for (let i = 0; i <= text.length; i++) {
        if (i < text.length && (mask & (1 << (text.charCodeAt(i) - 97))) !== 0) brokenWord = true;
        if (i === text.length || text[i] === ' ') {
            if (!brokenWord) count++;
            brokenWord = false;
        }
    }
    return count;
};