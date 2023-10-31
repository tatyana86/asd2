import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class aBSTTest {

    aBST arrayTree = new aBST(3);

    @BeforeEach
    void fillTree() {
        arrayTree.Tree[0] = 50;
        arrayTree.Tree[1] = 25;
        arrayTree.Tree[2] = 75;
        arrayTree.Tree[3] = null;
        arrayTree.Tree[4] = 37;
        arrayTree.Tree[5] = 62;
        arrayTree.Tree[6] = 84;
        arrayTree.Tree[7] = null;
        arrayTree.Tree[8] = null;
        arrayTree.Tree[9] = 31;
        arrayTree.Tree[10] = 43;
        arrayTree.Tree[11] = 55;
        arrayTree.Tree[12] = null;
        arrayTree.Tree[13] = null;
        arrayTree.Tree[14] = 92;
    }

    @Test
    public void findExistKey() {
        assertEquals(9, arrayTree.FindKeyIndex(31));
        assertEquals(14, arrayTree.FindKeyIndex(92));
    }

    @Test
    public void findNOT_ExistKeyIfExistEmptySlot() {
        assertEquals(-3, arrayTree.FindKeyIndex(20));
        assertEquals(-12, arrayTree.FindKeyIndex(65));
    }

    @Test
    public void findNOT_ExistKeyIfNOT_ExistEmptySlot() {
        arrayTree.Tree[3] = 20;
        arrayTree.Tree[7] = 18;
        arrayTree.Tree[8] = 22;
        arrayTree.Tree[12] = 64;
        arrayTree.Tree[13] = 80;

        assertNull(arrayTree.FindKeyIndex(15));
        assertNull(arrayTree.FindKeyIndex(100));
    }

    @Test
    public void addExistKey() {
        assertEquals(9, arrayTree.AddKey(31));
        assertEquals(14, arrayTree.AddKey(92));
    }

    @Test
    public void addNOT_ExistKeyIfExistEmptySlot() {
        assertEquals(3, arrayTree.AddKey(20));
        assertEquals(12, arrayTree.AddKey(65));
    }

    @Test
    public void addNOT_ExistKeyIfNOT_ExistEmptySlot() {
        arrayTree.Tree[3] = 20;
        arrayTree.Tree[7] = 18;
        arrayTree.Tree[8] = 22;
        arrayTree.Tree[12] = 64;
        arrayTree.Tree[13] = 80;

        assertEquals(-1, arrayTree.AddKey(15));
        assertEquals(-1, arrayTree.AddKey(100));
    }
}