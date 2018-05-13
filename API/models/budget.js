module.exports = function (sequelize, DataTypes) {
    const Budget  = sequelize.define('Budget', {
        id: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true
        },
        title: {
            type: DataTypes.STRING,
            allowNull: false
        },
        amount: {
            type: DataTypes.FLOAT,
            allowNull: false
        }
    }, {
        paranoid: false,
        underscored: true,
        freezeTableName: true
    });
    Budget.associate = _associate;
    return Budget;
};

function _associate(models) {
  models.Budget.belongsTo(models.Conference);

}
