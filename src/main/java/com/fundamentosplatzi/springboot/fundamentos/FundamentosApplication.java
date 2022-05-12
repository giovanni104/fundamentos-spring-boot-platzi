package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

    private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

    private ComponentDependency componentDependency;
    private MyBean myBean;
    private MyBeanWithDependency myBeanWithDependency;
    private MyBeanWithProperties myBeanWithProperties;
    private UserPojo userPojo;
    private UserRepository userRepository;

    public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
                                  MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo,UserRepository userRepository) {
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
        this.userRepository=userRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(FundamentosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       // ejemplosAnteriores();
        saveUsersInDataBase();

    }

    private void saveUsersInDataBase() {
        User user1 = new User("giovanny", "gio104@hotmail.com", LocalDate.of(2021, 05, 1));
        User user2 = new User("jenni", "jenni@hotmail.com", LocalDate.of(2021, 05, 2));
        User user3 = new User("nicole", "nicole@hotmail.com", LocalDate.of(2021, 05, 3));
        User user4 = new User("isabel", "isabel@hotmail.com", LocalDate.of(2021, 05, 4));
        User user5 = new User("melissa", "melissa@hotmail.com", LocalDate.of(2021, 05, 5));
        User user6 = new User("jorge", "jorge@hotmail.com", LocalDate.of(2021, 05, 6));
        User user7 = new User("camilo", "camilo@hotmail.com", LocalDate.of(2021, 05, 7));
        User user8 = new User("maria", "maria@hotmail.com", LocalDate.of(2021, 05, 8));
        User user9 = new User("andres", "andres@hotmail.com", LocalDate.of(2021, 05, 9));
        User user10 = new User("pedro", "pedro4@hotmail.com", LocalDate.of(2021, 05, 10));

        List<User> list= Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10);

        list.stream().forEach(userRepository::save);

    }


    private void ejemplosAnteriores() {
        componentDependency.saludar();
        myBean.print();
        myBeanWithDependency.printWithDependency();
        System.out.println(myBeanWithProperties.function());
        System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword());
        LOGGER.error("Esto es un error del aplicativo");

        try {
            int value = 10 / 0;
            LOGGER.debug("mi valor: " + value);
        } catch (Exception e) {

            LOGGER.error("Esto es un error del aplicativo " + e.getMessage());
        }


    }


}
