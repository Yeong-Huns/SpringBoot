import org.junit.jupiter.api.*;

public class JUnitCycleTest {
    @BeforeAll //전체 테스트를 시작하기 전에 1회 실행하므로 메서드는 static으로 선언
    static void beforeAll(){
        System.out.println("@BeforeAll");
    }
    @BeforeEach //테스트 케이스 시작전마다 실행 == public
    public void beforeEach(){
        System.out.println("@BeforeEach");
    }
    @Test
    public void test1(){
        System.out.println("test01");
    }
    @Test
    public void test2(){
        System.out.println("test02");
    }
    @Test
    public void test3(){
        System.out.println("test03");
    }
    @AfterAll //모든 테스트 케이스가 종료하기전 한번 실행 (마지막에 단 한번 실행 == static)
    static void AfterAll(){
        System.out.println("@AfterAll");
    }
    @AfterEach //테스트 케이스를 종료하기 전마다 실행 (매번실행이라 public)
    public void AfterEach(){
        System.out.println("@AfterEach");
    }
}
