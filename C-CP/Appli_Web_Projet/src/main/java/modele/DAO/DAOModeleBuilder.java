/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;

import java.sql.ResultSet;

/**
 *
 * @author loiseln
 * @param <T>
 */
public interface DAOModeleBuilder<T> {
    public T build(ResultSet rs) throws DAOException;
}
