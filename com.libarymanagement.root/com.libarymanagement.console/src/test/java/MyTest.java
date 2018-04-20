import org.junit.Test;

import java.time.*;
import java.util.Date;

/**
 * Created by Lee on 2018/4/2.
 */
public class MyTest {

    @Test
    public void test(){
        Instant loanInstant = new Date(2018,2,1).toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(loanInstant, zone);
        LocalDate loanLocalDate = localDateTime.toLocalDate();


        Instant repayInstant = new Date(2018,3,28).toInstant();

        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(repayInstant, zone);
        LocalDate repayLocalDate = localDateTime1.toLocalDate();

        Period between = Period.between(loanLocalDate, repayLocalDate);

        System.out.println(between.getDays());
    }


}
