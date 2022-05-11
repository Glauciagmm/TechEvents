package com.techevents.techevents.entity;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="events")
public class Events implements Serializable {

        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotEmpty
        @Column(name = "name")
        private String name;
        @NotEmpty
        private String date;
        @NotEmpty
        private String img;

        private int vacants;

        private int signed;

        @NotEmpty
        private String description;

        private boolean featured;
        @NotEmpty
        private String type;

       @ManyToMany (mappedBy = "events")

        private Set<Users> users = new HashSet<>() ;


        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getDate() {
                return date;
        }

        public void setDate(String date) {
                this.date = date;
        }

        public String getImg() {
                return img;
        }

        public void setImg(String img) {
                this.img = img;
        }

    public int getSigned() {
        return signed;
    }

    public void setSigned(int signed) {
        this.signed = signed;
    }

    public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public int getVacants() {
            return vacants;
        }

        public void setVacants(int vacants) {
            this.vacants = vacants;
        }

        public boolean isFeatured() {
            return featured;
        }

        public void setFeatured(boolean featured) {
            this.featured = featured;
        }

        public String getType() {
                    return type;
            }

        public void setType(String type) {
                this.type = type;
        }

        @Override
        public String toString() {
                return "Events{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", date='" + date + '\'' +
                        ", img='" + img + '\'' +
                        ", vacants=" + vacants +
                        ", description='" + description + '\'' +
                        ", featured=" + featured +
                        ", type='" + type + '\'' +
                        '}';
        }


}
