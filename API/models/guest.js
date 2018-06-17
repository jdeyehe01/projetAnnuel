
//Invité
module.exports = function (sequelize, DataTypes) {
    const Guest  = sequelize.define('Guest', {
        id: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true
        },
        fname: {
            type: DataTypes.STRING,
            allowNull: false
        },
        lname: {
            type: DataTypes.STRING,
            allowNull: false
        },
        email: {
            type: DataTypes.STRING,
            allowNull: false,
            validate: {
              isEmail: true
            }
          },
        isPresent : {
          type: DataTypes.TINYINT,
          allowNull: false,
          defaultValue: 0
        }
    }, {
        paranoid: false,
        underscored: true,
        freezeTableName: true
    });
    Guest.associate = _associate;
    return Guest;
};

function _associate(models) {
  models.Guest.belongsToMany(models.Conference, {
    as: 'conferences',
    through: 'GuestConference',
    foreignKey: 'guest_id'
  });
}
