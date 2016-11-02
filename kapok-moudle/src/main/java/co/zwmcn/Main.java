package co.zwmcn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Will WM. Zhang
 */
public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
        System.out.println(context.getBeanDefinitionNames().length);

        Integer[] times = {1, 3, 6, 12, 24, 48, 96, 192, 384};
        Integer[] peoples = {1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683};
        Integer total = 0;
        for (int i = 1; i <= times.length; i++) {
            total += times[i-1];
            if (i == 8) break;
        }
        System.out.println(total);

    }

}
