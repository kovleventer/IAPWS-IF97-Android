package com.kovlev.iapws_if97;

import android.content.Context;

/**
 * @author kovlev
 */

// TODO remove this class
public class Result {
    String mainCategory;
    public String mainUnit = "";

    public double result = -9998;


    public Result(Context context, String mainCategory, String param1Type, double param1, String param2Type, double param2) {
        this.mainCategory = mainCategory;
        if (mainCategory.equals(context.getString(R.string.param_temperature))) {
            mainUnit = "°C";
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_enthalpy)))
                result = XSteam.T_PH(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_spec_enthropy)))
                result = XSteam.T_PS(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_enthalpy)) && param2Type.equals(context.getString(R.string.param_spec_enthropy)))
                result = XSteam.T_HS(param1, param2);

        } else if (mainCategory.equals(context.getString(R.string.param_pressure))) {
            mainUnit = "bar";
            if (param1Type.equals(context.getString(R.string.param_enthalpy)) && param2Type.equals(context.getString(R.string.param_spec_enthropy)))
                result = XSteam.P_HS(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_enthalpy)) && param2Type.equals(context.getString(R.string.param_density)))
                result = XSteam.P_HRHO(param1, param2);

        } else if (mainCategory.equals(context.getString(R.string.param_enthalpy))) {
            mainUnit = "kJ/kg";
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_steam__)))
                result = XSteam.HV_P(param1);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_liquid__)))
                result = XSteam.HL_P(param1);
            if (param1Type.equals(context.getString(R.string.param_temperature)) && param2Type.equals(context.getString(R.string.param_steam__)))
                result = XSteam.HV_T(param1);
            if (param1Type.equals(context.getString(R.string.param_temperature)) && param2Type.equals(context.getString(R.string.param_liquid__)))
                result = XSteam.HL_T(param1);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_temperature)))
                result = XSteam.H_PT(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_spec_enthropy)))
                result = XSteam.H_PS(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_vapour_fraction)))
                result = XSteam.H_PX(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_temperature)) && param2Type.equals(context.getString(R.string.param_vapour_fraction)))
                result = XSteam.H_TX(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_density)))
                result = XSteam.H_PRHO(param1, param2);

        } else if (mainCategory.equals(context.getString(R.string.param_spec_volume))) {
            mainUnit = "m3/kg";
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_steam__)))
                result = XSteam.VV_P(param1);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_liquid__)))
                result = XSteam.VL_P(param1);
            if (param1Type.equals(context.getString(R.string.param_temperature)) && param2Type.equals(context.getString(R.string.param_steam__)))
                result = XSteam.VV_T(param1);
            if (param1Type.equals(context.getString(R.string.param_temperature)) && param2Type.equals(context.getString(R.string.param_liquid__)))
                result = XSteam.VL_T(param1);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_temperature)))
                result = XSteam.V_PT(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_enthalpy)))
                result = XSteam.V_PH(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_spec_enthropy)))
                result = XSteam.V_PS(param1, param2);

        } else if (mainCategory.equals(context.getString(R.string.param_density))) {
            mainUnit = "kg/m3";
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_steam__)))
                result = XSteam.RHOV_P(param1);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_liquid__)))
                result = XSteam.RHOL_P(param1);
            if (param1Type.equals(context.getString(R.string.param_temperature)) && param2Type.equals(context.getString(R.string.param_steam__)))
                result = XSteam.RHOV_T(param1);
            if (param1Type.equals(context.getString(R.string.param_temperature)) && param2Type.equals(context.getString(R.string.param_liquid__)))
                result = XSteam.RHOL_T(param1);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_temperature)))
                result = XSteam.RHO_PT(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_enthalpy)))
                result = XSteam.RHO_PH(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_spec_enthropy)))
                result = XSteam.RHO_PS(param1, param2);

        } else if (mainCategory.equals(context.getString(R.string.param_spec_enthropy))) {
            mainUnit = "kJ/(kg K)";
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_steam__)))
                result = XSteam.SV_P(param1);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_liquid__)))
                result = XSteam.SL_P(param1);
            if (param1Type.equals(context.getString(R.string.param_temperature)) && param2Type.equals(context.getString(R.string.param_steam__)))
                result = XSteam.SV_T(param1);
            if (param1Type.equals(context.getString(R.string.param_temperature)) && param2Type.equals(context.getString(R.string.param_liquid__)))
                result = XSteam.SL_T(param1);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_temperature)))
                result = XSteam.S_PT(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_enthalpy)))
                result = XSteam.S_PH(param1, param2);

        } else if (mainCategory.equals(context.getString(R.string.param_spec_int_energy))) {
            mainUnit = "kJ/kg";
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_steam__)))
                result = XSteam.UV_P(param1);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_liquid__)))
                result = XSteam.UL_P(param1);
            if (param1Type.equals(context.getString(R.string.param_temperature)) && param2Type.equals(context.getString(R.string.param_steam__)))
                result = XSteam.UV_T(param1);
            if (param1Type.equals(context.getString(R.string.param_temperature)) && param2Type.equals(context.getString(R.string.param_liquid__)))
                result = XSteam.UL_T(param1);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_temperature)))
                result = XSteam.U_PT(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_enthalpy)))
                result = XSteam.U_PH(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_spec_enthropy)))
                result = XSteam.U_PS(param1, param2);

        } else if (mainCategory.equals(context.getString(R.string.param_spec_ibar_heatcap))) {
            mainUnit = "kJ/(kg°C)";
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_steam__)))
                result = XSteam.CPV_P(param1);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_liquid__)))
                result = XSteam.CPL_P(param1);
            if (param1Type.equals(context.getString(R.string.param_temperature)) && param2Type.equals(context.getString(R.string.param_steam__)))
                result = XSteam.CPV_T(param1);
            if (param1Type.equals(context.getString(R.string.param_temperature)) && param2Type.equals(context.getString(R.string.param_liquid__)))
                result = XSteam.CPL_T(param1);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_temperature)))
                result = XSteam.CP_PT(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_enthalpy)))
                result = XSteam.CP_PH(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_spec_enthropy)))
                result = XSteam.CP_PS(param1, param2);

        } else if (mainCategory.equals(context.getString(R.string.param_spec_ichor_heatcap))) {
            mainUnit = "kJ/(kg°C)";
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_steam__)))
                result = XSteam.CVV_P(param1);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_liquid__)))
                result = XSteam.CVL_P(param1);
            if (param1Type.equals(context.getString(R.string.param_temperature)) && param2Type.equals(context.getString(R.string.param_steam__)))
                result = XSteam.CVV_T(param1);
            if (param1Type.equals(context.getString(R.string.param_temperature)) && param2Type.equals(context.getString(R.string.param_liquid__)))
                result = XSteam.CVL_T(param1);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_temperature)))
                result = XSteam.CV_PT(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_enthalpy)))
                result = XSteam.CV_PH(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_spec_enthropy)))
                result = XSteam.CV_PS(param1, param2);

        } else if (mainCategory.equals(context.getString(R.string.param_sound_speed))) {
            mainUnit = "m/s";
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_steam__)))
                result = XSteam.WV_P(param1);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_liquid__)))
                result = XSteam.WL_P(param1);
            if (param1Type.equals(context.getString(R.string.param_temperature)) && param2Type.equals(context.getString(R.string.param_steam__)))
                result = XSteam.WV_T(param1);
            if (param1Type.equals(context.getString(R.string.param_temperature)) && param2Type.equals(context.getString(R.string.param_liquid__)))
                result = XSteam.WL_T(param1);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_temperature)))
                result = XSteam.W_PT(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_enthalpy)))
                result = XSteam.W_PH(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_spec_enthropy)))
                result = XSteam.W_PS(param1, param2);

        } else if (mainCategory.equals(context.getString(R.string.param_dyn_viscosity))) {
            mainUnit = "Pa s";
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_temperature)))
                result = XSteam.MY_PT(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_enthalpy)))
                result = XSteam.MY_PH(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_spec_enthropy)))
                result = XSteam.MY_PS(param1, param2);

        } else if (mainCategory.equals(context.getString(R.string.param_prandtl))) {
            mainUnit = "";
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_temperature)))
                result = XSteam.PR_PT(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_enthalpy)))
                result = XSteam.PR_PH(param1, param2);

        } else if (mainCategory.equals(context.getString(R.string.param_thermal_cond))) {
            mainUnit = "W/(m K)";
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_steam__)))
                result = XSteam.TCV_P(param1);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_liquid__)))
                result = XSteam.TCL_P(param1);
            if (param1Type.equals(context.getString(R.string.param_temperature)) && param2Type.equals(context.getString(R.string.param_steam__)))
                result = XSteam.TCV_T(param1);
            if (param1Type.equals(context.getString(R.string.param_temperature)) && param2Type.equals(context.getString(R.string.param_liquid__)))
                result = XSteam.TCL_T(param1);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_temperature)))
                result = XSteam.TC_PT(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_enthalpy)))
                result = XSteam.TC_PH(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_enthalpy)) && param2Type.equals(context.getString(R.string.param_spec_enthropy)))
                result = XSteam.TC_HS(param1, param2);

        } else if (mainCategory.equals(context.getString(R.string.param_surface_tension))) {
            mainUnit = "N/m";
            if (param1Type.equals(context.getString(R.string.param_pressure)))
                result = XSteam.ST_P(param1);
            if (param1Type.equals(context.getString(R.string.param_temperature)))
                result = XSteam.ST_T(param1);

        } else if (mainCategory.equals(context.getString(R.string.param_vapour_fraction))) {
            mainUnit = "";
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_enthalpy)))
                result = XSteam.X_PH(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_spec_enthropy)))
                result = XSteam.X_PS(param1, param2);

        } else if (mainCategory.equals(context.getString(R.string.param_vapour_volume_fraction))) {
            mainUnit = "";
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_enthalpy)))
                result = XSteam.VX_PH(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_spec_enthropy)))
                result = XSteam.VX_PS(param1, param2);

        } else if (mainCategory.equals(context.getString(R.string.param_kappa))) {
            mainUnit = "";
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_temperature)))
                result = XSteam.Kappa_pT(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_temp_steam)))
                result = XSteam.Steam_Kappa_pt(param1, param2);
            if (param1Type.equals(context.getString(R.string.param_pressure)) && param2Type.equals(context.getString(R.string.param_enthalpy)))
                result = XSteam.Kappa_ph(param1, param2);

        } else if (mainCategory.equals(context.getString(R.string.param_sat_temperature))) {
            mainUnit = "°C";
            if (param1Type.equals(context.getString(R.string.param_pressure)))
                result = XSteam.TSAT_P(param1);
            if (param1Type.equals(context.getString(R.string.param_spec_enthropy)))
                result = XSteam.TSAT_S(param1);

        } else if (mainCategory.equals(context.getString(R.string.param_sat_pressure))) {
            mainUnit = "bar";
            if (param1Type.equals(context.getString(R.string.param_temperature)))
                result = XSteam.PSAT_T(param1);
            if (param1Type.equals(context.getString(R.string.param_spec_enthropy)))
                result = XSteam.PSAT_S(param1);

        }
    }
}
