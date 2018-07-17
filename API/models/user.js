module.exports = function (sequelize, DataTypes) {
    const User = sequelize.define('User', {
        id: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true,
            allowNull: false

        },
        email: {
            type: DataTypes.STRING,
            allowNull: false
        },
        password: {
            type: DataTypes.STRING,
            allowNull: false
        },
        emailConfirmed:{
          type: DataTypes.TINYINT,
          allowNull: false,
          defaultValue: 0
        }
    }, {
        paranoid: false,
        underscored: true,
        freezeTableName: true
    });
    User.associate = _associate;
    return User;
};

function _associate(models) {
  models.User.hasMany(models.Conference, {
    as: 'conferences',
    constraints: false
  });
}
