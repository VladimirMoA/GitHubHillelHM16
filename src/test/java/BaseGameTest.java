import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BaseGameTest{
    private BaseGame baseGame;

    @Test
    public void temporaryResultTest() {
        assertEquals(0,baseGame.temporaryResult(PossibleChoice.rock,PossibleChoice.rock));
    }
    @Test
    public void temporaryResultTest1() {
        assertEquals(1,baseGame.temporaryResult(PossibleChoice.rock,PossibleChoice.scissors));
    }
    @Test
    public void temporaryResultTest2() {
        assertEquals(2,baseGame.temporaryResult(PossibleChoice.rock,PossibleChoice.paper));
    }
}
