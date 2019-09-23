package moriyashiine.acme.common.capability.blessing;

import moriyashiine.acme.common.capability.blessing.blessings.Blessing;

public interface IBlessingCapability {
    Blessing getBlessing();

    void setBlessing(Blessing blessing);

    boolean isDirty();

    void setDirty(boolean dirty);
}
