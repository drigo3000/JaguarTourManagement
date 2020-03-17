/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.business.entities.hotel.qualifiers;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

/**
 *
 * @author rxkx
 */
@Qualifier
@Retention(RUNTIME)
@Target({FIELD, PARAMETER})
public @interface HotelEvent {

    Type value();

    enum Type {
        CREATE, UPDATE, DELETE, READ, UPDATE_GOAL
    }

}
