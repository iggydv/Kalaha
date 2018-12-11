//package com.ignatius.data.objects;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.event.EventListener;
//import org.springframework.web.socket.messaging.SessionConnectEvent;
//
//@SpringBootApplication
//public class Game {
//    private static Logger logger = LoggerFactory.getLogger(Game.class);
//
//    public static void main(String[] args) {
//        logger.info("Starting Spring boot Application");
//        SpringApplication.run(Game.class, args);
//    }
//    @EventListener(SessionConnectEvent.class)
//    public void handleWebsocketConnectListner(SessionConnectEvent event) {
//        logger.info("Received a new web socket connection");
//    }
//
////    @Service
////    public static class MyService {
////        public String sayHi() {
////            return "Hello Spring Initializr!";
////        }
////
////    }
////
////    @Theme("valo")
////    @SpringUI(path = "")
////    public static class VaadinUI extends UI {
////
////        @Autowired
////        MyService myService;
////
////        @Override
////        protected void init(VaadinRequest request) {
////            VerticalLayout v = new VerticalLayout();
////            v.setSizeFull();
////            window.s
////            setContent(new Label(myService.sayHi()));
////        }
////
////    }
//
//
//}
