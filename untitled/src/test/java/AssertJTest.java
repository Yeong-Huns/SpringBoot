import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AssertJTest {

    @DisplayName("AssertJ 테스트 입니다.")
    @Test
    public void sumTest(){
        int c = 4;
        int d = 5;
        int sum2= 9;
        Assertions.assertEquals(sum2, c+d);
    }

}
