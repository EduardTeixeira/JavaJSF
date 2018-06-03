package bean;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mapper.Cep;
import model.CepModel;

@ManagedBean
@ViewScoped
public class CepBean {

    ArrayList<Cep> listCep;
    CepModel model;
    Cep selectedCep;
    boolean edit = false;

    public void init() {
        model = new CepModel();
        getCeps();
    }

    public void novoCep() {
        selectedCep = new Cep();
        edit = false;
    }

    public void salvaCep() {
        try {
            if (edit) {
                model.update(selectedCep);
            } else {
                model.insert(selectedCep);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        getCeps();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", (edit ? "Atualizado" : "Inserido")));

    }

    public void deleteCep(Cep c) {
        try {
            model.delete(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getCeps();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Excluido!"));

    }

    public void getCeps() {
        listCep = model.getCeps();
    }

    public ArrayList<Cep> getListCep() {
        return listCep;
    }

    public void setListCep(ArrayList<Cep> listCep) {
        this.listCep = listCep;
    }

    public Cep getSelectedCep() {
        return selectedCep;
    }

    public void setSelectedCep(Cep selectedCep) {
        edit = true;
        this.selectedCep = selectedCep;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

}
