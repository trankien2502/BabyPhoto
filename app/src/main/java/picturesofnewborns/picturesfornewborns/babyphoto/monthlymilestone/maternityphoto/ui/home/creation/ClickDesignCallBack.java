package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.creation;


import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.design.DesignModel;

public interface ClickDesignCallBack {
    void select(DesignModel designModel);
    void detail(DesignModel designModel);
}
