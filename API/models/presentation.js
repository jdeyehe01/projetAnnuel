module.exports = function (sequelize, DataTypes) {
    const Presentation  = sequelize.define('Presentation', {
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
        },
        description: {
            type: DataTypes.STRING,
            allowNull: false
        }
    }, {
        paranoid: false,
        underscored: true,
        freezeTableName: true
    });
    Presentation.associate = _associate;
    return Presentation;
};

function _associate(models) {
  models.Presentation.belongsTo(models.Conference);

}
