package com.scriptlte.gopandas.modules.security.pojo.grant;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrgGrantType implements Serializable {
    private String type;
    private List<OrgGrant> grants = new ArrayList<>();
    private List<OrgGrantType> childTypes = new ArrayList<>();
}
