module.exports = function (sequelize, DataTypes) {
    const Task  = sequelize.define('Task', {
        id: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true
        },
        title: {
            type: DataTypes.STRING,
            allowNull: false
        },
        duration: {
            type: DataTypes.STRING,
            allowNull: false
        }
    }, {
        paranoid: false,
        underscored: true,
        freezeTableName: true
    });
    Task.associate = _associate;
    return Task;
};

function _associate(models) {
  models.Task.belongsTo(models.Conference);

}
