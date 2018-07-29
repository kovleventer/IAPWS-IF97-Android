package com.kovlev.iapws_if97;

import org.junit.Test;

import static org.junit.Assert.*;
import static com.kovlev.iapws_if97.XSteam.*;

/**
 * Test cases are from X-Steam tables (x-eng.com)
 */
public class XSteamTests {
    @Test
    public void XTest() {
        // TODO increase coverage of tests
        // Current coverage (XSteam): 70% method / 41% line
        double delta = 0.01;
        double deltaMY = 0.0001;

        // Temperature
        assertEquals(TSAT_P(1), 99.61, delta);
        assertEquals(T_PH(1, 100), 23.84, delta);
        assertEquals(T_PS(1, 1), 73.71, delta);
        assertEquals(T_HS(100, 0.2), 13.85, delta);

        // Pressure
        assertEquals(PSAT_T(100), 1.01, delta);
        assertEquals(P_HS(84, 0.296), 2.3, delta);
        assertEquals(P_HRHO(2000, 5), 6.05, delta);

        // Enthalpy
        assertEquals(HV_P(1), 2674.95, delta);
        assertEquals(HL_P(1), 417.44, delta);
        assertEquals(HV_T(100), 2675.57, delta);
        assertEquals(HL_T(100), 419.1, delta);
        assertEquals(H_PT(1.01325, 200), 2875.41, delta);
        assertEquals(H_PS(1, 1), 308.61, delta);
        assertEquals(H_PX(1, 0.5), 1546.19, delta);
        assertEquals(H_TX(100, 0.5), 1547.34, delta);
        assertEquals(H_PRHO(1, 2), 1082.77, delta);

        // Specific volume
        assertEquals(VV_P(1), 1.69, delta);
        assertEquals(VL_P(1), 0.00104, delta);
        assertEquals(VV_T(100), 1.67, delta);
        assertEquals(VL_T(100), 0, delta);
        assertEquals(V_PT(1, 100), 1.7, delta);
        assertEquals(V_PH(1, 1000), 0.44, delta);
        assertEquals(V_PS(1, 5), 1.03, delta);

        // Density
        assertEquals(RHOV_P(1), 0.59, delta);
        assertEquals(RHOL_P(1), 958.64, delta);
        assertEquals(RHOV_T(100), 0.6, delta);
        assertEquals(RHOL_T(100), 958.35, delta);
        assertEquals(RHO_PT(1, 100), 0.59, delta);
        assertEquals(RHO_PH(1, 1000), 2.28, delta);
        assertEquals(RHO_PS(1, 1), 975.62, delta);

        // Specific entropy
        assertEquals(SV_P(0.006117), 9.16, delta);
        assertEquals(SL_P(0.0061171), 0.00, delta);
        assertEquals(SV_T(0.0001), 9.16, delta);
        assertEquals(SL_T(100), 1.31, delta);
        assertEquals(S_PT(1, 20), 0.3, delta);
        assertEquals(S_PH(1, 2875.41), 7.84, delta);

        // Specific isobaric heat capacity
        assertEquals(CPV_P(1), 2.08, delta);
        assertEquals(CPL_P(1), 4.22, delta);
        assertEquals(CPV_T(100), 2.08, delta);
        assertEquals(CPL_T(100), 4.22, delta);
        assertEquals(CP_PT(1, 100), 2.07, delta);
        assertEquals(CP_PH(1, 200), 4.18, delta);
        assertEquals(CP_PS(1, 1), 4.19, delta);

        // Specific isochoric heat capacity
        assertEquals(CVV_P(1), 1.55, delta);
        assertEquals(CVL_P(1), 3.77, delta);
        assertEquals(CVV_T(100), 1.55, delta);
        assertEquals(CVL_T(100), 3.77, delta);
        assertEquals(CV_PT(1, 100), 1.55, delta);
        assertEquals(CV_PH(1, 200), 4.04, delta);
        assertEquals(CV_PS(1, 1), 3.9, delta);

        // Speed of sound
        assertEquals(WV_P(1), 472.05, delta);
        assertEquals(WL_P(1), 1545.45, delta);
        assertEquals(WV_T(100), 472.26, delta);
        assertEquals(WL_T(100), 1545.09, delta);
        assertEquals(W_PT(1, 100), 472.34, delta);
        assertEquals(W_PH(1, 200), 1542.68, delta);
        assertEquals(W_PS(1, 1), 1557.86, delta);

        // Dynamic viscosity
        assertEquals(MY_PT(1, 100), 0.00001, deltaMY);
        assertEquals(MY_PH(1, 100), 0.00091, deltaMY);
        assertEquals(MY_PS(1, 1), 0.00038, deltaMY);

        // Prandtl
        assertEquals(PR_PT(1, 200), 0.95781, delta);
        assertEquals(PR_PH(1, 2875.4750649489), 0.95781, delta);

        // Thermal Conductivity
        assertEquals(TCL_P(1), 0.678, delta);
        assertEquals(TCV_P(1), 0.025, delta);
        assertEquals(TCL_T(25), 0.607, delta);
        assertEquals(TCV_T(25), 0.018, delta);
        assertEquals(TC_PT(100, 350), 0.069, delta);
        assertEquals(TC_PH(1, 100), 0.606, delta);
        assertEquals(TC_HS(100, 0.34), 0.606, delta);

        // Surface Tension
        assertEquals(ST_T(100), 0.059, delta); // This test was done incorrectly in XSteam v2.6.ods
        assertEquals(ST_P(1), 0.059, delta);

        // Vapour fraction
        assertEquals(X_PH(1, 1000), 0.26, delta);
        assertEquals(X_PS(1, 4), 0.45, delta);

        // Vapor volume fraction
        assertEquals(VX_PH(1, 418), 0.2885, delta);
        assertEquals(VX_PS(1, 4), 0.9992, delta);
    }
}