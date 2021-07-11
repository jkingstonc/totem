package com.kingstonops.totem.ui;

public class UI {


    /*
    *
    * Panel p = new Panel(STRETCH);
    *
    * */

    public static final Constraint ZERO = new Constraint(Constraint.Mode.ABS, 0);
    public static final Constraint STRETCH = new Constraint(Constraint.Mode.REL, 1);
    public static final Constraint HALF = new Constraint(Constraint.Mode.REL, .5);


    public static final ConstraintBundle FILL = new ConstraintBundle(
            new Constraint(Constraint.Mode.REL, 0),
            new Constraint(Constraint.Mode.REL, 0),
            new Constraint(Constraint.Mode.REL, 1),
            new Constraint(Constraint.Mode.REL, 1)
    );


    public static class ConstraintBundle{
        public Constraint m_x_constraint;
        public Constraint m_y_constraint;
        public Constraint m_w_constraint;
        public Constraint m_h_constraint;

        public ConstraintBundle(){}
        public ConstraintBundle(Constraint x, Constraint y, Constraint w, Constraint h){
            m_x_constraint = x;
            m_y_constraint = y;
            m_w_constraint = w;
            m_h_constraint = h;
        }

        public ConstraintBundle x(Constraint constraint){
            m_x_constraint= constraint;
            return this;
        }
        public ConstraintBundle y(Constraint constraint){
            m_y_constraint= constraint;
            return this;
        }
        public ConstraintBundle w(Constraint constraint){
            m_w_constraint= constraint;
            return this;
        }
        public ConstraintBundle h(Constraint constraint){
            m_h_constraint= constraint;
            return this;
        }
    }
    public static class Constraint {
        public enum Mode{
            REL,
            ABS
        }
        public Mode m_mode;
        public double m_value;

        public Constraint(Mode mode, double value){
            m_mode = mode;
            m_value = value;
        }
    }


    public static class UIElement{
        public ConstraintBundle m_constraints;

        public UIElement(){}
        public UIElement(ConstraintBundle constraints){
            m_constraints = constraints;
        }
    }
}
