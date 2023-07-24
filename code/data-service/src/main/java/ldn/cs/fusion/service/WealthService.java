package ldn.cs.fusion.service;

import ldn.cs.fusion.dao.StaffDao;
import ldn.cs.fusion.dao.WealthDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WealthService {
    @Autowired
    private WealthDao wealth;
}
