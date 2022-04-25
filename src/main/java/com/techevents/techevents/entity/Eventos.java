package com.techevents.techevents.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="eventos")
public class Eventos implements Serializable{

        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String tipo;
        private String nombre;
        private String fecha;
        private String imagen;
        private int numeroParticipantes;
        private String descripción;
        private boolean destacado;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getTipo() {
                return tipo;
        }

        public void setTipo(String tipo) {
                this.tipo = tipo;
        }

        public String getNombre() {
                return nombre;
        }

        public void setNombre(String nombre) {
                this.nombre = nombre;
        }

        public String getFecha() {
                return fecha;
        }

        public void setFecha(String fecha) {
                this.fecha = fecha;
        }

        public String getImagen() {
                return imagen;
        }

        public void setImagen(String imagen) {
                this.imagen = imagen;
        }

        public int getNumeroParticipantes() {
                return numeroParticipantes;
        }

        public void setNumeroParticipantes(int numeroParticipantes) {
                this.numeroParticipantes = numeroParticipantes;
        }

        public String getDescripción() {
                return descripción;
        }

        public void setDescripción(String descripción) {
                this.descripción = descripción;
        }

        public boolean isDestacado() {
                return destacado;
        }

        public void setDestacado(boolean destacado) {
                this.destacado = destacado;
        }

        @Override
        public String toString() {
                return "Eventos{" +
                        "id=" + id +
                        ", tipo='" + tipo + '\'' +
                        ", nombre='" + nombre + '\'' +
                        ", fecha='" + fecha + '\'' +
                        ", imagen='" + imagen + '\'' +
                        ", numeroParticipantes=" + numeroParticipantes +
                        ", descripción='" + descripción + '\'' +
                        ", destacado=" + destacado +
                        '}';
        }


}
