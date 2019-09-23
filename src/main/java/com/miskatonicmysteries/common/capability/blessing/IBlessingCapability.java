package com.miskatonicmysteries.common.capability.blessing;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;

public interface IBlessingCapability {
    Blessing getBlessing();

    void setBlessing(Blessing blessing);

    boolean isDirty();

    void setDirty(boolean dirty);
}
