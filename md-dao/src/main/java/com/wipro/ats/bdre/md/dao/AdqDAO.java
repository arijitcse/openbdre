/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wipro.ats.bdre.md.dao;

import com.wipro.ats.bdre.exception.MetadataException;
import com.wipro.ats.bdre.md.dao.jpa.Adq;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by SU324335 on 3/8/2016.
 */
@Transactional
@Service
public class AdqDAO {
    private static final Logger LOGGER = Logger.getLogger(AdqDAO.class);
    @Autowired
    SessionFactory sessionFactory;

    public Adq get(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Adq adq = (Adq) session.get(Adq.class, id);
        session.getTransaction().commit();
        session.close();
        return adq;
    }

    public Integer insert(Adq adq) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Integer id = null;
        try {
            id = (Integer) session.save(adq);
            session.getTransaction().commit();
        } catch (MetadataException e) {
            session.getTransaction().rollback();
            LOGGER.error(e);
        } finally {
            session.close();
        }
        return id;
    }

    public void update(Adq adq) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(adq);
            session.getTransaction().commit();
        } catch (MetadataException e) {
            session.getTransaction().rollback();
            LOGGER.error(e);
        } finally {
            session.close();
        }
    }

    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Adq adq = (Adq) session.get(Adq.class, id);
            session.delete(adq);
            session.getTransaction().commit();
        } catch (MetadataException e) {
            session.getTransaction().rollback();
            LOGGER.error(e);
        } finally {
            session.close();
        }
    }
}
