class DNode {
    DListNode lastNode;
    DNode pre;
    DNode next;
    int count;
    DNode() {}
    DNode(DListNode node) { this.lastNode = node; }
    DNode(DListNode node, int count) { this.lastNode = node; this.count = count; }
}
class DListNode {
    String key;
    DListNode pre;
    DListNode next;
    DNode group;
    DListNode() {}
    DListNode(String key) { this.key = key; }
}
class AllOne {
    DNode head;
    DNode tail;
    HashMap<String, DListNode> keyMap;
    public AllOne() {
        keyMap = new HashMap<>();
        head = null;
        tail = null;
    }
    public void inc(String key) {
        DListNode target;
        DNode curGroup, nextGroup, newGroup;
        if (keyMap.containsKey(key)) {
            target = keyMap.get(key);
            curGroup = target.group;
            nextGroup = curGroup.next;
            if (nextGroup == null || nextGroup.count > curGroup.count + 1) {
                if (curGroup.lastNode == target && target.pre == null) {
                    curGroup.count++;
                } else {
                    newGroup = new DNode(target, curGroup.count + 1);
                    target.group = newGroup;
                    newGroup.next = nextGroup;
                    if (nextGroup != null) {
                        nextGroup.pre = newGroup;
                    }
                    if (curGroup.lastNode == target) {
                        curGroup.lastNode = target.pre;
                    }
                    curGroup.next = newGroup;
                    newGroup.pre = curGroup;
                    if (curGroup == tail) {
                        tail = newGroup;
                    }
                    if (target.pre != null) {
                        target.pre.next = target.next;
                    }
                    if (target.next != null) {
                        target.next.pre = target.pre;
                    }
                    target.pre = null;
                    target.next = null;
                }
            } else {
                if (curGroup.lastNode == target) {
                    if (target.pre == null) {
                        if (curGroup.pre != null) {
                            curGroup.pre.next = nextGroup;
                        }
                        nextGroup.pre = curGroup.pre;
                        if (head == curGroup) {
                            head = nextGroup;
                        }
                    } else {
                        curGroup.lastNode = target.pre;
                    }
                }
                if (target.pre != null) {
                    target.pre.next = target.next;
                }
                if (target.next != null) {
                    target.next.pre = target.pre;
                }
                target.next = null;
                nextGroup.lastNode.next = target;
                target.pre = nextGroup.lastNode;
                nextGroup.lastNode = target;
                target.group = nextGroup;
            }
        } else {
            target = new DListNode(key);
            keyMap.put(key, target);
            if (head == null || head.count > 1) {
                newGroup = new DNode(target);
                if (head == null) {
                    tail = newGroup;
                } else {
                    newGroup.next = head;
                    head.pre = newGroup;
                }
                head = newGroup;
                target.group = newGroup;
                newGroup.count = 1;
            } else {
                head.lastNode.next = target;
                target.pre = head.lastNode;
                head.lastNode = target;
                target.group = head;
            }
        }
    }
    public void dec(String key) {
        DListNode target = keyMap.get(key);
        DNode curGroup = target.group, preGroup = curGroup.pre, newGroup;
        if (curGroup.count == 1) {
            if (curGroup.lastNode == target) {
                if (target.pre == null) {
                    if (curGroup.next != null) {
                        curGroup.next.pre = null;
                    }
                    head = curGroup.next;
                    if (head == null) {
                        tail = null;
                    }
                } else {
                    curGroup.lastNode = target.pre;
                }
            } else {
                if (target.next != null) {
                    target.next.pre = target.pre;
                }
                if (target.pre != null) {
                    target.pre.next = target.next;
                }
            }
            keyMap.remove(key);
        } else if (preGroup == null || preGroup.count < curGroup.count - 1) {
            if (curGroup.lastNode == target && target.pre == null) {
                curGroup.count--;
            } else {
                newGroup = new DNode(target, curGroup.count - 1);
                target.group = newGroup;
                newGroup.pre = preGroup;
                if (preGroup != null) {
                    preGroup.next = newGroup;
                }
                if (curGroup.lastNode == target) {
                    curGroup.lastNode = target.pre;
                }
                curGroup.pre = newGroup;
                newGroup.next = curGroup;
                if (curGroup == head) {
                    head = newGroup;
                }
                if (target.pre != null) {
                    target.pre.next = target.next;
                }
                if (target.next != null) {
                    target.next.pre = target.pre;
                }
                target.pre = null;
                target.next = null;
            }
        } else {
            if (curGroup.lastNode == target) {
                if (target.pre == null) {
                    if (curGroup.next != null) {
                        curGroup.next.pre = preGroup;
                    }
                    preGroup.next = curGroup.next;
                    if (tail == curGroup) {
                        tail = preGroup;
                    }
                } else {
                    curGroup.lastNode = target.pre;
                }
            }
            if (target.pre != null) {
                target.pre.next = target.next;
            }
            if (target.next != null) {
                target.next.pre = target.pre;
            }
            target.next = null;
            preGroup.lastNode.next = target;
            target.pre = preGroup.lastNode;
            preGroup.lastNode = target;
            target.group = preGroup;
        }
    }
    public String getMaxKey() {
        if (tail == null) {
            return "";
        }
        return tail.lastNode.key;
    }
    public String getMinKey() {
        if (head == null) {
            return "";
        }
        return head.lastNode.key;
    }

    public void test() {
        DNode h = head;
        DListNode n;
        while (h != null) {
            System.out.print(h.lastNode.key);
            System.out.print(' ');
            System.out.print(h.count);
            System.out.print(" #### ");
            n = h.lastNode;
            while (n != null) {
                System.out.print(n.key);
                System.out.print(' ');
                n = n.pre;
            }
            System.out.println();
            h = h.next;
        }
    }
}