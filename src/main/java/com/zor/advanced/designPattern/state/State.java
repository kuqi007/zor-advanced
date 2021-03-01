package com.zor.advanced.designPattern.state;

/**
 * Created by kuqi0 on 2021/3/1
 */
// 定义一个抽象的状态类
public abstract class State {
    Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public abstract void handle1();

    public abstract void handle2();
}

// 定义状态A
class ConcreteStateA extends State {

    @Override
    public void handle1() {
        // 本状态下必须处理要处理的事
    }

    @Override
    public void handle2() {
        // 切换到状态B
        super.context.setCurrentState(Context.concretStateB);
        // 执行状态B的任务
        super.context.handle2();
    }
}

// 定义状态B
class ConcreteStateB extends State {

    @Override
    public void handle2() {
        // 本状态下必须处理要处理的事
    }

    @Override
    public void handle1() {
        // 切换到状态A
        super.context.setCurrentState(Context.concretStateA);
        // 执行状态A的任务
        super.context.handle1();

    }
}

// 定义一个上下文管理环境
class Context {
    public final static ConcreteStateA concretStateA = new ConcreteStateA();
    public final static ConcreteStateB concretStateB = new ConcreteStateB();

    private State CurrentState;

    public State getCurrentState() {
        return CurrentState;
    }

    public void setCurrentState(State currentState) {
        this.CurrentState = currentState;
    }

    public void handle1() {
        this.CurrentState.handle1();
    }

    public void handle2() {
        this.CurrentState.handle2();
    }
}

class Client {
    public static void main(String[] args) {
        Context context = new Context();
        context.setCurrentState(new ConcreteStateA());
        context.handle1();
        context.handle2();
    }
}