package bd1.obli2012.gui.backend;

import bd1.obli2012.versionado.BDDVersionado;
import bd1.obli2012.versionado.Cambio;
import bd1.obli2012.versionado.VersionBDD;
import bd1.obli2012.versionado.helper.VersionadoHelper;
import java.util.ArrayList;
import java.util.List;

public class Contexto {

    private static Contexto instancia;
    private BDDVersionado bddVersionado;
    private Integer version;
    
    public static Contexto getInstance() {
        if (instancia == null) {
            instancia = new Contexto();
        }
        return instancia;
    }

    public BDDVersionado getBDDVersionado() {
        return this.bddVersionado;
    }

    private Contexto() {
    }

    public void seleccionarBaseDeDatos(String nombreBaseDeDatos) {
        if (bddVersionado != null) {
            if (!bddVersionado.getNombreBDD().equals(nombreBaseDeDatos)) {
                this.bddVersionado = VersionadoHelper.obtenerVersiones(nombreBaseDeDatos);
                this.version = this.bddVersionado.getVersionActual();
            
            }
        } else {
            this.bddVersionado = VersionadoHelper.obtenerVersiones(nombreBaseDeDatos);
            this.version = this.bddVersionado.getVersionActual();
            
        }
    }

    public void guardarCambio(Cambio cambio) {
        Integer ver = this.version + 1;
        VersionBDD v = this.bddVersionado.getVersiones().get(ver);
        this.bddVersionado.setVersionActual(ver);
        if(v == null){
            v = new VersionBDD();
            v.setVersion(ver);
            this.bddVersionado.getVersiones().put(ver, v);
        }
            v.getCambios().add(cambio);
           
        VersionadoHelper.salvarCambios(bddVersionado);
    }
/*
    private void guardarCambiosEnCola() {
        if (this.colaCambios != null && !this.colaCambios.isEmpty()) {
            Integer numVersion = bddVersionado.getVersionActual() + 1;

            VersionBDD version = new VersionBDD();
            version.setVersion(numVersion);

            List<Cambio> cambios = new ArrayList<Cambio>();
            cambios.addAll(this.colaCambios);
            version.setCambios(cambios);
            bddVersionado.getVersiones().put(numVersion, version);
            bddVersionado.setVersionActual(numVersion);
            VersionadoHelper.salvarCambios(bddVersionado);
            this.colaCambios = new ArrayList<Cambio>();
            this.hayCambios = false;
        }
    }
  */  

    public void salvar() {
        VersionadoHelper.salvarCambios(this.bddVersionado);
    }
}