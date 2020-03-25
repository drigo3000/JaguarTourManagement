package com.swevolution.jaguartourmanagement.model.entities.util;

/*
 * Copyright 2014 Rxkx.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Rxkx
 */
@MappedSuperclass
public class PK_String_Entity extends GeneralEntity implements Serializable {

    @Id
    @NotNull
    @Size(min = 1, max = 200)
    @Column(nullable = false, length = 200, name = "ID")
    protected String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.login);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PK_String_Entity other = (PK_String_Entity) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        return true;
    }

}
