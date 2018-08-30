package com.kovlev.iapws_if97;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    // Error codes used
    public static final double ERROR_XSTEAM_OOR = -9999;
    public static final double ERROR_XSTEAM_TM = -9998;
    public static final double ERROR_XSTEAM_IT = -9997;

    Spinner mainSelector;

    Spinner param1Selector;
    Spinner param2Selector;

    EditText param1Field;
    EditText param2Field;

    TextView param1ME;
    TextView param2ME;

    TextView result;

    // Formatting
    //NumberFormat formatter2 = new DecimalFormat("#0.00");
    private static final int DEFAULT_PRECISION = 2;
    private int precision = DEFAULT_PRECISION;

    String numberFormatErrorMessage;
    String typeMismatchErrorMessage;
    String outOfRangeErrorMessage;
    String iterationErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberFormatErrorMessage = getString(R.string.error_nf);
        typeMismatchErrorMessage = getString(R.string.error_tm);
        outOfRangeErrorMessage = getString(R.string.error_oor);
        iterationErrorMessage = getString(R.string.error_it);

        mainSelector = (Spinner) findViewById(R.id.main_selector);
        ArrayAdapter<CharSequence> adapterMainSelector = ArrayAdapter.createFromResource(this, R.array.main_categories, android.R.layout.simple_spinner_item);
        adapterMainSelector.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mainSelector.setAdapter(adapterMainSelector);

        param1Selector = (Spinner) findViewById(R.id.param1);
        param2Selector = (Spinner) findViewById(R.id.param2);

        param1Field = (EditText) findViewById(R.id.param1f);
        param2Field = (EditText) findViewById(R.id.param2f);

        param1ME = (TextView) findViewById(R.id.param1_me);
        param2ME = (TextView) findViewById(R.id.param2_me);

        result = (TextView) findViewById(R.id.result_text_view);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setPrecision(sharedPreferences);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        mainSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selctedItemView, int position, long id) {
                setParamSpinner(position, 1);
                setParamSpinner(position, 2);
                if (param1Selector.getSelectedItemPosition() != 0
                        || param2Selector.getSelectedItemPosition() != 0
                        || mainSelector.getSelectedItem().toString().equals(getString(R.string.param_temperature))
                        || mainSelector.getSelectedItem().toString().equals(getString(R.string.param_pressure))
                        || mainSelector.getSelectedItem().toString().equals(getString(R.string.param_surface_tension))
                        || mainSelector.getSelectedItem().toString().equals(getString(R.string.param_vapour_fraction))
                        || mainSelector.getSelectedItem().toString().equals(getString(R.string.param_vapour_volume_fraction))
                        || mainSelector.getSelectedItem().toString().equals(getString(R.string.param_kappa))
                        || mainSelector.getSelectedItem().toString().equals(getString(R.string.param_sat_temperature))
                        || mainSelector.getSelectedItem().toString().equals(getString(R.string.param_sat_pressure))
                        ) {
                    param1Field.setText("");
                    param2Field.setText("");
                }

                // Hides the second parameter group if necessary
                if (mainSelector.getSelectedItem().toString().equals(getString(R.string.param_surface_tension))
                        || mainSelector.getSelectedItem().toString().equals(getString(R.string.param_sat_temperature))
                        || mainSelector.getSelectedItem().toString().equals(getString(R.string.param_sat_pressure))
                        ) {
                    findViewById(R.id.to_hide1).setVisibility(View.INVISIBLE);
                    findViewById(R.id.to_hide2).setVisibility(View.INVISIBLE);
                } else {
                    findViewById(R.id.to_hide1).setVisibility(View.VISIBLE);
                    findViewById(R.id.to_hide2).setVisibility(View.VISIBLE);
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        // TODO code cleanup
        // Setting unit codes
        param1Selector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selctedItemView, int position, long id) {
                String s = param1Selector.getSelectedItem().toString();
                if (s.equals(getString(R.string.param_pressure))) {
                    param1ME.setText("bar");

                } else if (s.equals(getString(R.string.param_enthalpy))) {
                    param1ME.setText("kJ/kg");

                } else if (s.equals(getString(R.string.param_spec_enthropy))) {
                    param1ME.setText("kJ/(kg K)");

                } else if (s.equals(getString(R.string.param_density))) {
                    param1ME.setText("kg/m3");

                } else if (s.equals(getString(R.string.param_temperature))) {
                    param1ME.setText("°C");

                } else if (s.equals(getString(R.string.param_vapour_fraction))) {
                    param1ME.setText("");

                } else if (s.equals(getString(R.string.param_temp_steam))) {
                    param1ME.setText("°C");

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // TODO code cleanup
        param2Selector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selctedItemView, int position, long id) {
                String s = param2Selector.getSelectedItem().toString();
                if (s.equals(getString(R.string.param_pressure))) {
                    param2ME.setText("bar");
                    setEditTextEditable(param2Field);

                } else if (s.equals(getString(R.string.param_enthalpy))) {
                    param2ME.setText("kJ/kg");
                    setEditTextEditable(param2Field);

                } else if (s.equals(getString(R.string.param_spec_enthropy))) {
                    param2ME.setText("kJ/(kg K)");
                    setEditTextEditable(param2Field);

                } else if (s.equals(getString(R.string.param_density))) {
                    param2ME.setText("kg/m3");
                    setEditTextEditable(param2Field);

                } else if (s.equals(getString(R.string.param_temperature))) {
                    param2ME.setText("°C");
                    setEditTextEditable(param2Field);

                } else if (s.equals(getString(R.string.param_vapour_fraction))) {
                    param2ME.setText("");
                    setEditTextEditable(param2Field);

                } else if (s.equals(getString(R.string.param_temp_steam))) {
                    param2ME.setText("°C");
                    setEditTextEditable(param2Field);

                } else if (s.equals(getString(R.string.param_steam__))) {
                    param2ME.setText("");
                    param2Field.setText("");
                    setEditTextNotEditable(param2Field);

                } else if (s.equals(getString(R.string.param_liquid__))) {
                    param2ME.setText("");
                    param2Field.setText("");
                    setEditTextNotEditable(param2Field);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    /**
     * Sets an EditText not editable by any means
     * @param editText The EditText to set
     */
    private void setEditTextNotEditable(EditText editText) {
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
        editText.setClickable(false);
    }

    /**
     * Sets an EditText editable by any means
     * @param editText The EditText to set
     */
    private void setEditTextEditable(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.setClickable(true);
    }

    // TODO code cleanup
    /**
     * Sets up spinner items in the desired parameter type list
     * @param position The position in result type list
     * @param i First or second list
     */
    private void setParamSpinner(int position, int i) {
        ArrayAdapter<CharSequence> adapterParamSelector = null;
        switch (position) {
            case 0:
                if (i == 1)
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.temp_p1, android.R.layout.simple_spinner_item);
                else
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.temp_p2, android.R.layout.simple_spinner_item);
                break;
            case 1:
                if (i == 1)
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.pres_p1, android.R.layout.simple_spinner_item);
                else
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.pres_p2, android.R.layout.simple_spinner_item);
                break;
            case 2:
                if (i == 1)
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.enth_p1, android.R.layout.simple_spinner_item);
                else
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.enth_p2, android.R.layout.simple_spinner_item);
                break;
            case 3:
                if (i == 1)
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.spvo_p1, android.R.layout.simple_spinner_item);
                else
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.spvo_p2, android.R.layout.simple_spinner_item);
                break;
            case 4:
                if (i == 1)
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.dens_p1, android.R.layout.simple_spinner_item);
                else
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.dens_p2, android.R.layout.simple_spinner_item);
                break;
            case 5:
                if (i == 1)
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.spen_p1, android.R.layout.simple_spinner_item);
                else
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.spen_p2, android.R.layout.simple_spinner_item);
                break;
            case 6:
                if (i == 1)
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.spie_p1, android.R.layout.simple_spinner_item);
                else
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.spie_p2, android.R.layout.simple_spinner_item);
                break;
            case 7:
                if (i == 1)
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.sbhc_p1, android.R.layout.simple_spinner_item);
                else
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.sbhc_p2, android.R.layout.simple_spinner_item);
                break;
            case 8:
                if (i == 1)
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.schc_p1, android.R.layout.simple_spinner_item);
                else
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.schc_p2, android.R.layout.simple_spinner_item);
                break;
            case 9:
                if (i == 1)
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.ssnd_p1, android.R.layout.simple_spinner_item);
                else
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.ssnd_p2, android.R.layout.simple_spinner_item);
                break;
            case 10:
                if (i == 1)
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.dynv_p1, android.R.layout.simple_spinner_item);
                else
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.dynv_p2, android.R.layout.simple_spinner_item);
                break;
            case 11:
                if (i == 1)
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.pran_p1, android.R.layout.simple_spinner_item);
                else
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.pran_p2, android.R.layout.simple_spinner_item);
                break;
            case 12:
                if (i == 1)
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.thco_p1, android.R.layout.simple_spinner_item);
                else
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.thco_p2, android.R.layout.simple_spinner_item);
                break;
            case 13:
                if (i == 1)
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.sute_p1, android.R.layout.simple_spinner_item);
                else
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.sute_p2, android.R.layout.simple_spinner_item);
                break;
            case 14:
                if (i == 1)
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.vapf_p1, android.R.layout.simple_spinner_item);
                else
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.vapf_p2, android.R.layout.simple_spinner_item);
                break;
            case 15:
                if (i == 1)
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.vavf_p1, android.R.layout.simple_spinner_item);
                else
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.vavf_p2, android.R.layout.simple_spinner_item);
                break;
            case 16:
                if (i == 1)
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.kapp_p1, android.R.layout.simple_spinner_item);
                else
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.kapp_p2, android.R.layout.simple_spinner_item);
                break;
            case 17:
                if (i == 1)
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.satt_p1, android.R.layout.simple_spinner_item);
                else
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.satt_p2, android.R.layout.simple_spinner_item);
                break;
            case 18:
                if (i == 1)
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.satp_p1, android.R.layout.simple_spinner_item);
                else
                    adapterParamSelector = ArrayAdapter.createFromResource(this, R.array.satp_p2, android.R.layout.simple_spinner_item);
                break;
        }
        assert adapterParamSelector != null;
        adapterParamSelector.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (i == 1)
            param1Selector.setAdapter(adapterParamSelector);
        else
            param2Selector.setAdapter(adapterParamSelector);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.key_precision))) {
            setPrecision(sharedPreferences);
        }
    }

    public enum Error {
        NUMBER_FORMAT_ERROR, TYPE_MISMATCH_ERROR, OUT_OF_RANGE_ERROR, NO_ERROR, ITERATION_ERROR
    }

    /**
     * Does the calculation by calling the correct method
     * @param view The clicked view
     */
    public void selectMethod (View view) {
        double param1Value;
        double param2Value = 0;
        double resultValue;

        try {
            param1Value = Double.parseDouble(param1Field.getText().toString());
            if (findViewById(R.id.to_hide2).getVisibility() == View.VISIBLE && param2Field.isClickable())
                param2Value = Double.parseDouble(param2Field.getText().toString());
        } catch (NumberFormatException nfe) {
            writeText(numberFormatErrorMessage, Error.NUMBER_FORMAT_ERROR);
            return;
        }

        String mainType = mainSelector.getSelectedItem().toString();
        String param1Type = param1Selector.getSelectedItem().toString();
        String param2Type = param2Selector.getSelectedItem().toString();
        Result result = new Result(this, mainType, param1Type, param1Value, param2Type, param2Value);
        resultValue = result.result;
        if (Math.abs(resultValue-ERROR_XSTEAM_TM) < 0.1)
            writeText(typeMismatchErrorMessage, Error.TYPE_MISMATCH_ERROR);
        else if (Math.abs(resultValue-ERROR_XSTEAM_OOR) < 0.1)
            writeText(outOfRangeErrorMessage, Error.OUT_OF_RANGE_ERROR);
        else if (Math.abs(resultValue-ERROR_XSTEAM_IT) < 0.1)
            writeText(iterationErrorMessage, Error.ITERATION_ERROR);
        else
            writeText(Utils.createFormatter(precision).format(resultValue) + " " + result.mainUnit, Error.NO_ERROR);
    }

    /**
     * Writes a colored text to 'result'
     * @param s The string to write
     * @param error Error determines the color of the string
     */
    private void writeText(String s, Error error) {
        Context context = this.getBaseContext();
        switch (error) {
            case NUMBER_FORMAT_ERROR:
                result.setTextColor(ContextCompat.getColor(context, R.color.colorNumberFormatError));
                break;
            case TYPE_MISMATCH_ERROR:
                result.setTextColor(ContextCompat.getColor(context, R.color.colorTypeMismatchError));
                break;
            case OUT_OF_RANGE_ERROR:
                result.setTextColor(ContextCompat.getColor(context, R.color.colorOutOfRangeError));
                break;
            case NO_ERROR:
                result.setTextColor(ContextCompat.getColor(context, R.color.colorNoError));
                break;
            case ITERATION_ERROR:
                result.setTextColor(ContextCompat.getColor(context, R.color.colorIterationError));
        }
        result.setText(s);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.help_id:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.text_about);
                try {
                    builder.setMessage(getString(R.string.app_name) + " " +
                            this.getPackageManager().getPackageInfo(getPackageName(), 0).versionName
                            + "\n" + getString(R.string.about) + "\n" + getString(R.string.created_by));
                } catch (PackageManager.NameNotFoundException ignored) {}
                builder.setIcon(R.drawable.icon);
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                builder.setCancelable(true);
                builder.create().show();
                return true;
            case R.id.settings_id:
                Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
                startActivity(startSettingsActivity);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setPrecision(SharedPreferences sp) {
        // EditTextPreference stores the value as a string
        try {
            precision = Integer.parseInt(sp.getString(getString(R.string.key_precision), Integer.toString(precision)));
        } catch (NumberFormatException nfe) {
            precision = DEFAULT_PRECISION;
        }
    }
}
