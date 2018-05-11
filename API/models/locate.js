module.exports = function (sequelize, DataTypes) {
    const Locate = sequelize.define('Locate', {
        id: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true
        },
        name: {
          type: DataTypes.STRING,
          allowNull: false
        },
        address: {
            type: DataTypes.STRING,
            allowNull: false
          },
          cityCode: {
              type: DataTypes.INTEGER,
              allowNull: false
          },
          city: {
              type: DataTypes.STRING,
              allowNull: false
          },
        }, {
            paranoid: false,
            underscored: true,
            freezeTableName: true
        });

        Locate.associate = _associate;
        return Locate;
    };

    function _associate(models) {

        models.Locate.belongsToMany(models.Conference , {
          as: 'conferences',
          through: 'ConferenceLocate',
          foreignKey: 'locate_id'
        });

      }
