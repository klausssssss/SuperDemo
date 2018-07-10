package com.demo.Repository.Repository;

import com.demo.Model.HelloWorld.Hello;
import com.demo.Repository.Base.BaseRepository;
import com.demo.Repository.IRepository.IHelloRepository;
import org.springframework.stereotype.Repository;

@Repository
public class HelloRepository extends BaseRepository<Hello,Integer> implements IHelloRepository {
    //private Logger logger = LoggerFactory.getLogger(HelloDao.class);

}
