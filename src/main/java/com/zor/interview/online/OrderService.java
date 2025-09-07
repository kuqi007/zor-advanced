package com.zor.interview.online;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 蔚来二面
 * 一个运单类Order包含如下字段：收货人姓名，发货城市，到货城市，收货地址等
 * 现在有一批运单，需要根据特定规则将他们分组，比如说按照到达城市分组，之后这些规则可能会发生改变，请预留拓展点
 * todo 本质是把order类转换为map的key。。。啥意思
 */
public class OrderService {
    public static void main(String[] args) {


        Order a = new Order();
        a.username = "11";
        a.toCity = "杭州";

        Order b = new Order();
        b.username = "22";
        b.toCity = "上海";

        Order c = new Order();
        c.username = "33";
        c.toCity = "上海";

        List<Order> input = new ArrayList<>();
        input.add(a);
        input.add(b);
        input.add(c);

        OrderService orderService = new OrderService();
        Map<String, List<Order>> res = orderService.groupOrder(input);

        res.forEach((i, list) -> {
            System.out.println(i);
            System.out.println(list.toString());
        });
    }


    interface Rule {

        void process(List<Order> orders);

    }

    public Map<String, List<Order>> groupOrder(List<Order> orders) {

        GroupRules rule = new RuleFactory().getRule("a");
        Map<String, List<Order>> map = rule.group(orders, orders1 -> {

        });
        return map;
    }


    class RuleFactory {
        GroupRules getRule(String params) {
            if ("a".equals(params)) {
                return new AGroupRule();
            }
            return null;
        }
    }

    interface GroupRules {

        Map<String, List<Order>> group(List<Order> orders, Rule rule);

    }

    class AGroupRule implements GroupRules {

        public Map<String, List<Order>> group(List<Order> orders, Rule rule) {
            Map<String, List<Order>> map = new HashMap<>();
            //for (Order order : orders) {
            //    String toCity = order.toCity;
            //    if (map.containsKey(toCity)) {
            //        List<Order> list = map.get(toCity);
            //        list.add(order);
            //    } else {
            //        List<Order> list = new ArrayList<>();
            //        list.add(order);
            //        map.put(toCity, list);
            //    }
            //
            //}

            //  Map<String,List<Order>> map = fun.apply(orders);
            return orders.stream().collect(Collectors.groupingBy(Order::getToCity));
        }


    }

    static class Order {


        public String username;
        public String fromCity;
        public String toCity;
        public String address;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getFromCity() {
            return fromCity;
        }

        public void setFromCity(String fromCity) {
            this.fromCity = fromCity;
        }

        public String getToCity() {
            return toCity;
        }

        public void setToCity(String toCity) {
            this.toCity = toCity;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "username='" + username + '\'' +
                    ", fromCity='" + fromCity + '\'' +
                    ", toCity='" + toCity + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }
    }
}