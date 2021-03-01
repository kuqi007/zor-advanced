package com.zor.advanced.designPattern.chain;

/**
 * Created by kuqi0 on 2021/3/1
 */
// 定义一个抽象的handle
public abstract class Handler {
    // 指向下一个处理者
    private Handler nextHandler;
    // 处理者能够处理的级别
    private int level;

    public Handler(int level) {
        this.level = level;
    }

    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    // 处理请求传递，注意final，子类不可重写
    public final void handleMessage(Request request) {
        if (level == request.getResultLevel()) {
            this.echo(request);
        } else {
            if (this.nextHandler != null) {
                this.nextHandler.handleMessage(request);
            } else {
                System.out.println("已经到尽头了");
            }
        }

    }

    // 抽象方法，子类实现
    public abstract void echo(Request request);
}

class HandleRuleA extends Handler {

    public HandleRuleA(int level) {
        super(level);
    }

    @Override
    public void echo(Request request) {
        System.out.println("我是处理者1，我正在处理A规则");
    }
}

class HandleRuleB extends Handler {

    public HandleRuleB(int level) {
        super(level);
    }

    @Override
    public void echo(Request request) {
        System.out.println("我是处理者2，我正在处理A规则");
    }
}

class Request {

    private int resultLevel;

    public int getResultLevel() {
        return resultLevel;
    }

    public void setResultLevel(int resultLevel) {
        this.resultLevel = resultLevel;
    }
}


class Client {
    public static void main(String[] args) {
        HandleRuleA handleRuleA = new HandleRuleA(1);
        HandleRuleB handleRuleB = new HandleRuleB(2);
        // 这是重点，将handleA和handleB串起来
        handleRuleA.setNextHandler(handleRuleB);
        handleRuleA.echo(new Request());
    }
}
