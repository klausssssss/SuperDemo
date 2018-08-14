package com.demo.Design.Factory;

public class Factory {

    public interface provider {
        public model produce();
    }

    public class model{

    }

    public class ssmodel extends model{

    }

    public class ssprovider implements provider{
        @Override
        public model produce(){
            return new ssmodel();
        }
    }
}
